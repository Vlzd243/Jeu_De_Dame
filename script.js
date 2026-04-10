const TAILLE = 10;
const plateauEl = document.getElementById('plateau');
const infoTourEl = document.getElementById('info-tour');

// Codes pour la grille : 
// 0 = vide, 1 = pion blanc, 2 = pion noir, 3 = dame blanche, 4 = dame noire
let grille = [];
let tourBlanc = true;
let selL = -1;
let selC = -1;

// Initialisation de la grille 10x10
function initialiserJeu() {
    grille = Array.from({ length: TAILLE }, () => Array(TAILLE).fill(0));
    
    for (let l = 0; l < TAILLE; l++) {
        for (let c = 0; c < TAILLE; c++) {
            if ((l + c) % 2 !== 0) {
                if (l < 4) grille[l][c] = 2;      // Noirs en haut
                else if (l > 5) grille[l][c] = 1; // Blancs en bas
            }
        }
    }
    dessinerPlateau();
}

function gererClic(l, c) {
    let pieceVal = grille[l][c];
    let estPieceBlanche = pieceVal === 1 || pieceVal === 3;
    let estPieceNoire = pieceVal === 2 || pieceVal === 4;

    // 1. Sélectionner une pièce
    if (selL === -1 && selC === -1) {
        if (pieceVal !== 0) {
            if ((tourBlanc && estPieceBlanche) || (!tourBlanc && estPieceNoire)) {
                selL = l;
                selC = c;
            }
        }
    } 
    // 2. Tenter un déplacement
    else {
        if (pieceVal === 0 && (l + c) % 2 !== 0) { // Case vide et sombre
            tenterDeplacement(l, c);
        } else {
            // Annuler la sélection si on clique ailleurs
            selL = -1;
            selC = -1;
        }
    }
    dessinerPlateau();
}

function tenterDeplacement(destL, destC) {
    let dL = destL - selL;
    let dC = destC - selC;
    let pieceVal = grille[selL][selC];
    let estDame = pieceVal === 3 || pieceVal === 4;
    
    let deplacementValide = false;
    let capture = false;
    let caseCaptureL = -1, caseCaptureC = -1;

    if (!estDame) {
        // Mouvement simple (1 case diagonale avant)
        if (Math.abs(dC) === 1) {
            if ((tourBlanc && dL === -1) || (!tourBlanc && dL === 1)) {
                deplacementValide = true;
            }
        } 
        // Prise simple (saut par dessus)
        else if (Math.abs(dC) === 2 && Math.abs(dL) === 2) {
            caseCaptureL = selL + dL / 2;
            caseCaptureC = selC + dC / 2;
            let pieceSautee = grille[caseCaptureL][caseCaptureC];
            
            let sauteAdversaire = false;
            if (tourBlanc && (pieceSautee === 2 || pieceSautee === 4)) sauteAdversaire = true;
            if (!tourBlanc && (pieceSautee === 1 || pieceSautee === 3)) sauteAdversaire = true;

            if (sauteAdversaire) {
                deplacementValide = true;
                capture = true;
            }
        }
    } else {
        // Déplacement très simplifié de la Dame (diagonale libre sans obstacle)
        if (Math.abs(dL) === Math.abs(dC)) {
            deplacementValide = true;
        }
    }

    if (deplacementValide) {
        // Effectuer le mouvement
        grille[destL][destC] = pieceVal;
        grille[selL][selC] = 0;
        
        if (capture) {
            grille[caseCaptureL][caseCaptureC] = 0;
        }

        // Promotion en Dame
        if (!estDame) {
            if (tourBlanc && destL === 0) grille[destL][destC] = 3;
            if (!tourBlanc && destL === TAILLE - 1) grille[destL][destC] = 4;
        }

        // Changer de tour
        tourBlanc = !tourBlanc;
        infoTourEl.textContent = tourBlanc ? "Tour des Blancs" : "Tour des Noirs";
        infoTourEl.style.color = tourBlanc ? "#333" : "#d32f2f";
        
        // Réinitialiser la sélection
        selL = -1;
        selC = -1;
    }
}

function dessinerPlateau() {
    plateauEl.innerHTML = ''; // Vider le plateau
    
    for (let l = 0; l < TAILLE; l++) {
        for (let c = 0; c < TAILLE; c++) {
            const caseDiv = document.createElement('div');
            
            // Couleur de la case
            let estSombre = (l + c) % 2 !== 0;
            caseDiv.className = 'case ' + (estSombre ? 'sombre' : 'claire');
            
            // Case sélectionnée
            if (l === selL && c === selC) {
                caseDiv.classList.add('selectionnee');
            }

            // Ajout d'une pièce si nécessaire
            let pieceVal = grille[l][c];
            if (pieceVal !== 0) {
                const pieceDiv = document.createElement('div');
                pieceDiv.classList.add('piece');
                if (pieceVal === 1 || pieceVal === 3) pieceDiv.classList.add('blanche');
                if (pieceVal === 2 || pieceVal === 4) pieceDiv.classList.add('noire');
                if (pieceVal === 3 || pieceVal === 4) pieceDiv.classList.add('dame');
                caseDiv.appendChild(pieceDiv);
            }

            // Événement de clic
            caseDiv.addEventListener('click', () => gererClic(l, c));
            plateauEl.appendChild(caseDiv);
        }
    }
}

// Lancer le jeu au chargement
initialiserJeu();