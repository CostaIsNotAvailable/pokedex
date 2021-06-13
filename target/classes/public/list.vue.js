const List = {
    template : `
        <div>
            <v-autocomplete label="Type" v-model="selectedType" :items="types" item-value="value" item-text="label" item clearable hide-details dense filled />
            <v-list two-line>
                <template v-for="(pokemon, index) in pokemons" >
                    <v-list-item :key="index">
                        <v-list-item-avatar>
                            <v-img :src="pokemon.thumbnail"></v-img>
                        </v-list-item-avatar>

                        <v-list-item-content>
                            <v-list-item-title>{{ pokemon.name }}</v-list-item-title>
                            <v-list-item-subtitle>ID:{{ pokemon.id }}</v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-row dense>
                                <v-col>
                                    <v-btn icon :to="getPokemonDetailUrl(pokemon)">
                                        <v-icon color="grey lighten-1">mdi-information</v-icon>
                                    </v-btn>
                                </v-col>

                                <v-col>
                                    <v-btn icon :to="getPokemonFormUrl(pokemon)">
                                        <v-icon color="grey lighten-1">mdi-pencil-circle</v-icon>
                                    </v-btn>
                                </v-col>

                                <v-col>
                                    <v-btn icon>
                                        <v-icon color="grey lighten-1">mdi-delete-circle</v-icon>
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-list-item-action>
                    </v-list-item>
                    <v-divider v-if="index < pokemons.length - 1" :key="'divider'+index"  />
                </template>    
            </v-list>
        </div>
    `,

    data(){
        return {
            pokemons: [],
            selectedType: "",
            types: []
        }
    },

    created(){
        this.types = TYPES;
        this.getPokemons();
    },

    methods: {
        async getPokemons() {
            const response = await axios.get("http://localhost:8080/pokemon");

            this.pokemons = response.data;
        },

        async getPokemonsByType(type) {
            const response = await axios.get("http://localhost:8080/pokemon?type=" + type);

            this.pokemons = response.data;
        },

        getPokemonDetailUrl(pokemon){
            return "/" + pokemon.id;
        },

        getPokemonFormUrl(pokemon){
            return "/" + pokemon.id + "/edit"
        }
    },

    watch:{
        selectedType: function(type){
            type !== null && type !== "" ? this.getPokemonsByType(type) : this.getPokemons();
        }
    }
}