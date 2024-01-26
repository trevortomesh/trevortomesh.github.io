document.addEventListener('DOMContentLoaded', () => {
    // Fetch data from JSON file
    fetch('nodes.json')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => initializeGraph(data))
        .catch(error => console.error('Error loading JSON:', error));

    // Function to initialize the graph
    function initializeGraph(data) {
        const graphContainer = document.getElementById('graph-container');

        const links = generateLinks(data);
        const nodes = generateNodes(data);

        const width = 600;
        const height = 400;

        const svg = d3.select(graphContainer).append("svg")
            .attr("width", width)
            .attr("height", height);

        const simulation = d3.forceSimulation(nodes)
            .force("link", d3.forceLink(links).id(d => d.id))
            .force("charge", d3.forceManyBody())
            .force("center", d3.forceCenter(width / 2, height / 2));

        const link = svg.append("g")
            .attr("stroke", "#999")
            .attr("stroke-opacity", 0.6)
            .selectAll("line")
            .data(links)
            .join("line")
            .attr("stroke-width", d => Math.sqrt(d.value));

        const node = svg.append("g")
            .selectAll("circle")
            .data(nodes)
            .join("circle")
            .attr("r", 8)
            .attr("fill", "#1f78b4")
            .call(drag(simulation))
            .on("mouseover", handleMouseOver)
            .on("mouseout", handleMouseOut);

        node.append("title")
            .text(d => d.title);

        simulation.on("tick", () => {
            link
                .attr("x1", d => d.source.x)
                .attr("y1", d => d.source.y)
                .attr("x2", d => d.target.x)
                .attr("y2", d => d.target.y);

            node
                .attr("cx", d => d.x)
                .attr("cy", d => d.y);
        });

        // Function to generate links based on shared tags
        function generateLinks(data) {
            const links = [];
            const tagMap = new Map();

            Object.keys(data).forEach(id => {
                const node = data[id];
                const tags = node.tags || [];

                tags.forEach(tag => {
                    if (!tagMap.has(tag)) {
                        tagMap.set(tag, []);
                    }

                    tagMap.get(tag).push(node);
                });
            });

            tagMap.forEach(nodesWithTag => {
                for (let i = 0; i < nodesWithTag.length; i++) {
                    for (let j = i + 1; j < nodesWithTag.length; j++) {
                        links.push({ source: nodesWithTag[i].id, target: nodesWithTag[j].id, value: 1 });
                    }
                }
            });

            return links;
        }

        // Function to generate nodes
        function generateNodes(data) {
            return Object.keys(data).map(id => ({ id, ...data[id] }));
        }

        // Function to handle mouseover event
        function handleMouseOver(event, d) {
            const details = `${d.title}\n${d.text}`;
            d3.select(this).attr("fill", "#ff7f00"); // Change node color on hover
            showDetails(details);
        }

        // Function to handle mouseout event
        function handleMouseOut(event, d) {
            d3.select(this).attr("fill", "#1f78b4"); // Restore node color on mouseout
            hideDetails();
        }

        // Function to show details
        function showDetails(details) {
            // Replace this with your logic to display details (e.g., tooltip)
            console.log(details);
        }

        // Function to hide details
        function hideDetails() {
            // Replace this with your logic to hide details (e.g., hide tooltip)
            console.log("Hide details");
        }

        // Function to enable dragging for nodes
        function drag(simulation) {
            function dragstarted(event, d) {
                if (!event.active) simulation.alphaTarget(0.3).restart();
                d.fx = d.x;
                d.fy = d.y;
            }

            function dragged(event, d) {
                d.fx = event.x;
                d.fy = event.y;
            }

            function dragended(event, d) {
                if (!event.active) simulation.alphaTarget(0);
                d.fx = null;
                d.fy = null;
            }

            return d3.drag()
                .on("start", dragstarted)
                .on("drag", dragged)
                .on("end", dragended);
        }
    }
});
