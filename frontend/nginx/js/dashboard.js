const PYTHON_API_BASE = 'http://localhost:5000';

async function carregarGrafico(endpoint, elementId) {
    const container = document.getElementById(elementId);
    if (!container) return;

    try {
        const response = await fetch(`${PYTHON_API_BASE}${endpoint}`);
        if (!response.ok) throw new Error('Erro ao buscar grafico');

        const fig = await response.json();
        const data = fig.data || [];
        const layout = fig.layout || {};
        const config = {
            responsive: true,
            displaylogo: false,
            modeBarButtonsToRemove: ['select2d', 'lasso2d']
        };

        await Plotly.newPlot(container, data, layout, config);
    } catch (error) {
        container.innerHTML = '<div class="placeholder-text">Erro ao carregar grafico.</div>';
        console.error(error);
    }
}

function carregarDashboard() {
    carregarGrafico('/api/graficos/evolucao-saldo?meses=12', 'chart-saldo');
    carregarGrafico('/api/graficos/receitas-despesas?meses=12', 'chart-receitas-despesas');
    carregarGrafico('/api/graficos/produtos-vendidos?limite=10', 'chart-produtos');
    carregarGrafico('/api/graficos/produtos-vencimento?dias=30&limite=10', 'chart-vencimento');
    carregarGrafico('/api/graficos/estoque-critico?limite=5', 'chart-estoque');
}

document.addEventListener('DOMContentLoaded', () => {
    carregarDashboard();
    const btnRefresh = document.querySelector('.btn-refresh');
    if (btnRefresh) {
        btnRefresh.addEventListener('click', (event) => {
            event.preventDefault();
            carregarDashboard();
        });
    }
});
