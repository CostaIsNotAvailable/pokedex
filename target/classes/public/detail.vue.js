const Detail = {
    template : `
        <div v-if="pokemon !== null">
            <v-list-item>
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
            <v-divider />

            <v-row dense class="mt-2">
                <v-col cols="12" md="9">
                    {{ pokemonDescription }}
                </v-col>
                <v-col cols="12" md="3">

                    <div class="d-flex justify-end">
                        <v-chip small>
                            <v-avatar left>
                                <v-icon color="grey lighten-1">mdi-heart-half-full</v-icon>
                            </v-avatar>
                            {{ pokemon.health }}
                        </v-chip> 
                        <v-chip v-for="(type, index) in pokemonTypes" :key="index" class="ml-1" small>
                            {{ type }}
                        </v-chip>
                    </div>
                </v-col>
            </v-row>
 
            <v-list two-line >
                <v-subheader>Évolutions</v-subheader>
                <template v-for="(p, index) in pokemon.evolutions" >
                    <v-list-item :key="index">
                        <v-list-item-avatar>
                            <v-img :src="p.thumbnail"></v-img>
                        </v-list-item-avatar>

                        <v-list-item-content>
                            <v-list-item-title>{{ p.name }}</v-list-item-title>
                            <v-list-item-subtitle>ID:{{ p.id }}</v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-row dense>
                                <v-col>
                                    <v-btn icon :to="getPokemonDetailUrl(p)">
                                        <v-icon color="grey lighten-1">mdi-information</v-icon>
                                    </v-btn>
                                </v-col>

                                <v-col>
                                    <v-btn icon :to="getPokemonFormUrl(p)">
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
                    <v-divider v-if="index < pokemon.evolutions.length - 1" :key="'divider'+index"  />
                </template>    
            </v-list>

            <v-list two-line >
                <v-subheader>Attaques</v-subheader>
                <template v-for="(attack, index) in pokemon.attacks" >
                    <v-list-item :key="index">

                        <v-list-item-content>
                            <v-list-item-title>{{ attack.name }}</v-list-item-title>
                            <v-list-item-subtitle>ID:{{ attack.id }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider v-if="index < pokemon.attacks.length - 1" :key="'divider'+index"  />
                </template>    
            </v-list>
        </div>
    `,

    data(){
        return {
            pokemon: null,
            types: []
        }
    },

    created(){
        this.types = TYPES;
        this.getPokemon();
    },

    methods:{
        async getPokemon(){
            const response = await axios.get("http://localhost:8080/pokemon/" + this.$route.params.id )

            this.pokemon = response.data;
        },

        getPokemonDetailUrl(pokemon){
            return "/" + pokemon.id;
        },

        getPokemonFormUrl(pokemon){
            return "/" + pokemon.id + "/edit"
        }
    },

    computed:{
        pokemonTypes() {
            if(this.pokemon == null){
                return [];
            }

            const pokemonTypes = [];
            this.pokemon.types.forEach(pt => {
                const type = this.types.find(t => t.value == pt);
                if(type){
                    pokemonTypes.push(type.label);
                }
            })

            return pokemonTypes;
        },

        pokemonDescription() {
            const emptyDescription = "Ce pokémon ne possède pas de description."

            return this.pokemon == null || this.pokemon.description == null ? emptyDescription : pokemon.description
        }
    }
}