import java.io.Serializable;

class Command implements Serializable {
    int number;
    long id;
    String name, governorName;
    Float x;
    Float y;
    Integer area, timezone;
    int population;
    float metersAboveSeaLevel;
    int capital;
    Government government;

    public Command(int number, long id, String name, Float x, Float y, Integer area, int population, float metersAboveSeaLevel,
                   Integer timezone, int capital, Government government,  String governorName){
        this.number = number;
        this.id = id;
        this.name = name;
        this.governorName = governorName;
        this.x = x;
        this.y = y;
        this.area = area;
        this.timezone = timezone;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.capital = capital;
        this.government = government;

    }

    public Command(int number, String name){
        this.number = number;
        this.name = name;
    }

    public Command(int number){
        this.number = number;
    }

    public Command(int number, long id){
        this.number = number;
        this.id = id;
    }

    public Command(int number, String name, Float x, Float y, Integer area, int population, float metersAboveSeaLevel,
                   Integer timezone, int capital, Government government,  String governorName){
        this.number = number;
        this.name = name;
        this.governorName = governorName;
        this.x = x;
        this.y = y;
        this.area = area;
        this.timezone = timezone;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.capital = capital;
        this.government = government;

    }

    @Override
    public String toString() {
        return "Command{" +
                "number=" + number +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", governorName='" + governorName + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", area=" + area +
                ", timezone=" + timezone +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", capital=" + capital +
                ", government=" + government +
                '}';
    }
}