function renderGraph(data) {
    const container = document.getElementById('graph');
    const width = container.clientWidth;
    const height = container.clientHeight;

    // Create the main SVG
    const svg = d3.select("#graph")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

    // Create a group for zoomable content
    const zoomLayer = svg.append("g");

    // Define zoom behavior
    const zoom = d3.zoom()
        .scaleExtent([0.5, 5]) // Zoom range: 50% to 500%
        .on("zoom", (event) => {
            zoomLayer.attr("transform", event.transform);
        });

    // Apply zoom behavior to the SVG
    svg.call(zoom);

    // Create nodes and links
    const nodes = data.courses.map(course => ({
        id: course.id,
        name: course.name
    }));

    const links = data.courses.flatMap(course =>
        course.prerequisites.map(prereq => ({
            source: prereq,
            target: course.id
        }))
    );

    // Force simulation
    const simulation = d3.forceSimulation(nodes)
        .force("link", d3.forceLink(links).id(d => d.id).distance(100))
        .force("charge", d3.forceManyBody().strength(-300))
        .force("center", d3.forceCenter(width / 2, height / 2));

    // Draw links
    const link = zoomLayer.append("g")
        .selectAll("line")
        .data(links)
        .join("line")
        .attr("class", "link")
        .style("stroke", "#999")
        .style("stroke-width", 1.5);

    // Draw nodes
    const node = zoomLayer.append("g")
        .selectAll("g")
        .data(nodes)
        .join("g")
        .attr("class", "node");

    node.append("circle")
        .attr("r", 10)
        .style("fill", "lightblue")
        .style("stroke", "gray")
        .style("stroke-width", 1.5);

    node.append("text")
        .text(d => d.name)
        .attr("dy", -15)
        .attr("text-anchor", "middle");

    // Update simulation
    simulation.on("tick", () => {
        link
            .attr("x1", d => d.source.x)
            .attr("y1", d => d.source.y)
            .attr("x2", d => d.target.x)
            .attr("y2", d => d.target.y);

        node
            .attr("transform", d => `translate(${d.x},${d.y})`);
    });

    // Zoom buttons functionality
    const zoomIn = () => {
        svg.transition().duration(500).call(zoom.scaleBy, 1.2); // Zoom in by 20%
    };

    const zoomOut = () => {
        svg.transition().duration(500).call(zoom.scaleBy, 0.8); // Zoom out by 20%
    };

    // Attach events to buttons
    document.getElementById("zoom-in").onclick = zoomIn;
    document.getElementById("zoom-out").onclick = zoomOut;
}