if (window.mermaid) {
  window.mermaid.initialize({
    startOnLoad: true,
    theme: 'base',
    securityLevel: 'loose',
    themeVariables: {
      background: '#ffffff',
      primaryColor: '#ffffff',
      primaryTextColor: '#0f172a',
      primaryBorderColor: '#1e3a5f',
      lineColor: '#1e3a5f',
      tertiaryColor: '#f3f7fc',
      edgeLabelBackground: '#ffffff',
      classText: '#0f172a',
      fontFamily: 'Source Sans 3, Segoe UI, sans-serif'
    }
  });
}

const classInfoDialog = document.getElementById('class-info-dialog');
const classInfoTitle = document.getElementById('class-info-title');
const classInfoBody = document.getElementById('class-info-body');
const classInfoClose = document.getElementById('class-info-close');
const classInfoTriggers = document.querySelectorAll('.class-info-trigger');

const classDescriptions = {
  TrinityModelApp:
    'Coordinates the whole demonstration. It retrieves singleton instances for DivineNature, Father, Son, and HolySpirit and prints doctrinally annotated outputs for unity of essence, relations of origin, aitia closure, and temporal missions.',
  DivineNature:
    'Encapsulates the one divine essence in the model as a strict singleton. Its fields represent core predicates (eternal, omnipotent, omniscient, simple) and are shared by all three divine persons to express one undivided nature.',
  DivinePerson:
    'Abstract base class for personhood in the model. It carries shared state (name, full divinity, distinct personhood, and possession of DivineNature) and requires subclasses to provide their relation-of-origin description.',
  Father:
    'Concrete DivinePerson representing the Father as a strict singleton. It models eternal begetting of the Son, eternal spiration of the Spirit (with the Son in Western articulation), temporal mission language, and the relation descriptor "unbegotten; principle without principle."',
  Son:
    'Concrete DivinePerson representing the Son as a strict singleton. It models eternal spiration of the Spirit (with the Father), temporal mission language, and the relation descriptor "eternally begotten of the Father."',
  HolySpirit:
    'Concrete DivinePerson representing the Holy Spirit as a strict singleton. It primarily supplies the relation-of-origin descriptor that the Spirit eternally proceeds from the Father and the Son as from one principle.'
};

if (classInfoDialog && classInfoTitle && classInfoBody && classInfoClose) {
  classInfoTriggers.forEach((trigger) => {
    trigger.addEventListener('click', () => {
      const className = trigger.getAttribute('data-class');
      if (!className) return;

      classInfoTitle.textContent = className;
      classInfoBody.textContent = classDescriptions[className] || 'No description available.';
      classInfoDialog.showModal();
    });
  });

  classInfoClose.addEventListener('click', () => {
    classInfoDialog.close();
  });

  classInfoDialog.addEventListener('click', (event) => {
    const rect = classInfoDialog.getBoundingClientRect();
    const clickedBackdrop =
      event.clientX < rect.left ||
      event.clientX > rect.right ||
      event.clientY < rect.top ||
      event.clientY > rect.bottom;
    if (clickedBackdrop) {
      classInfoDialog.close();
    }
  });
}
