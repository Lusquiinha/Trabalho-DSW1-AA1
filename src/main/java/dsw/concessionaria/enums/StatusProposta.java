package dsw.concessionaria.enums;

public enum StatusProposta {
    ABERTO("Aberto"),
    ACEITO("Aceito"),
    NAO_ACEITO("Não Aceito");

    private final String descricao;

    StatusProposta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}