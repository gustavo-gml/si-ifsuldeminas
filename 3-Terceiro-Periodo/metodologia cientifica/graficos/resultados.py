import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

# 1. Estruturação dos dados da tabela
data = {
    'Período': [
        '3 Meses',
        '1 Ano (12m)',
        '3 Anos (36m)',
        '5 Anos (60m)',
        '10 Anos (120m)',
    ],
    'On-Premise': [178610.83, 204485.33, 273483.98, 342482.64, 684965.28],
    'AWS': [16737.77, 66951.09, 200853.26, 334755.43, 669510.86],
    'Azure': [15745.44, 62981.77, 188945.30, 314908.84, 629817.67],
}

df = pd.DataFrame(data)

# 2. Configurações visuais do gráfico
plt.style.use(
    'seaborn-v0_8-whitegrid'
    if 'seaborn-v0_8-whitegrid' in plt.style.available
    else 'default'
)

x = np.arange(len(df['Período']))

# Largura das barras e espaçamento (gap) entre elas dentro de cada grupo
bar_width = 0.22
gap = 0.04

x1 = x - (bar_width + gap)
x2 = x
x3 = x + (bar_width + gap)

colors = {
    'On-Premise': '#2B3A4A',  # Grafite
    'AWS': '#FF9900',  # Laranja AWS
    'Azure': '#0089D6',  # Azul Azure
}

# 3. Criando a figura compacta
fig, ax = plt.subplots(figsize=(12, 4.5), dpi=300)

rects1 = ax.bar(
    x1,
    df['On-Premise'] / 1000,
    bar_width,
    label='On-Premise (Local)',
    color=colors['On-Premise'],
    alpha=0.9,
)
rects2 = ax.bar(
    x2,
    df['AWS'] / 1000,
    bar_width,
    label='TCO Nuvem AWS',
    color=colors['AWS'],
    alpha=0.9,
)
rects3 = ax.bar(
    x3,
    df['Azure'] / 1000,
    bar_width,
    label='TCO Nuvem Azure\n(Mais Econômico)',
    color=colors['Azure'],
    alpha=0.9,
)

# 4. Formatação de eixos, grid e bordas
ax.set_ylabel(
    'Custo Total (em Milhares de R$)',
    fontsize=10,
    fontweight='bold',
    labelpad=10,
    color='#333333',
)
ax.set_xticks(x)
ax.set_xticklabels(df['Período'], fontsize=10, fontweight='medium')

ax.grid(axis='y', linestyle='--', alpha=0.4, color='#cccccc')
ax.set_axisbelow(True)

for spine in ['top', 'right', 'left', 'bottom']:
  ax.spines[spine].set_visible(False)


# 5. Adicionando rótulos levemente inclinados (25°) para evitar sobreposição
def add_labels_slanted(rects):
  for rect in rects:
    height = rect.get_height()
    val_str = (
        f'R$ {height:,.1f}k'.replace('.', 'v').replace(',', '.').replace('v', ',')
    )
    ax.annotate(
        val_str,
        xy=(rect.get_x() + rect.get_width() / 2, height),
        xytext=(0, 4),
        textcoords='offset points',
        ha='left',
        va='bottom',
        fontsize=8,
        fontweight='bold',
        color='#222222',
        rotation=25,
    )


add_labels_slanted(rects1)
add_labels_slanted(rects2)
add_labels_slanted(rects3)

# 6. Legenda lateral externa e escala Y
ax.legend(
    bbox_to_anchor=(1.02, 1),
    loc='upper left',
    frameon=True,
    facecolor='#f8f9fa',
    edgecolor='none',
    fontsize=9.5,
)
ax.yaxis.set_major_formatter('R$ {x:,.0f}k')
ax.set_ylim(0, max(df['On-Premise'] / 1000) * 1.25)

plt.tight_layout()

# Salvar gráfico final
plt.savefig('tco_espacado_legivel.png', dpi=300, bbox_inches='tight')
plt.show()