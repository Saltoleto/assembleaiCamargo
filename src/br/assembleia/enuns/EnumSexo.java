package br.assembleia.enuns;

/**
 *
 * @author fernandosaltoleto
 */
public enum EnumSexo {

    MASCULINO("Masculino"), FEMININO("Feminino");

    private final String descricao; 

    private EnumSexo(String descricao) {
        this.descricao = descricao;     
    }

    public static EnumSexo getEstado(String sexo) {
        if (sexo != null) {
            for (EnumSexo e : values()) {
                if (sexo.equalsIgnoreCase(e.name())) {
                    return e;
                }
            }
        }
        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
