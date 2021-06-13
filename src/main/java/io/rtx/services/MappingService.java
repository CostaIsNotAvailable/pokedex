package io.rtx.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import io.rtx.dtos.pokeapi.LinkDto;
import io.rtx.dtos.pokeapi.MoveDto;
import io.rtx.dtos.pokeapi.NameDto;
import io.rtx.dtos.pokeapi.StatDto;
import io.rtx.dtos.pokeapi.TypeDto;
import io.rtx.entities.Attack;
import io.rtx.entities.Pokemon;
import io.rtx.enums.Type;

public class MappingService {

	public static void addMappings(ModelMapper modelMapper) {
		MoveDtoToAttackMapping(modelMapper);
		PokemonDtoToPokemonMapping(modelMapper);
	}
	
	//Mapping Methods
	private static void MoveDtoToAttackMapping(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<MoveDto, Attack>(){
			@Override
			protected void configure() {
				map(source.getId(), destination.getId());
				using(toFrenchName).map(source.getNames(), destination.getName());
				using(typeDtoToEnum).map(source.getType(), destination.getType());
				map(source.getPower(), destination.getPower());
				map(source.getAccuracy(), destination.getAccuracy());
		    	map(0, destination.getCost());
			}
		});
	}
	
	private static void PokemonDtoToPokemonMapping(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<io.rtx.dtos.pokeapi.PokemonDto, Pokemon>(){
			@Override
			protected void configure() {
				map(source.getId(), destination.getId());
				using(getHealthFromStats).map(source.getStats(), destination.getHealth());
				using(typeDtoCollectionToEnumCollection).map(source.getTypes(), destination.getTypes());
				map(source.getSprites().getFront_default(), destination.getThumbnail());
			}
		});
	}
	
	//Converters
	private static Converter<Collection<NameDto>,String> toFrenchName = new Converter<Collection<NameDto>,String>() {
		public String convert(MappingContext<Collection<NameDto>,String> context) {
			return context.getSource() == null ? null : context.getSource().stream().filter(i -> i.getLanguage().getName().equals("fr")).collect(Collectors.toList()).get(0).getName();
		}
	};
	
	private static Converter<LinkDto, Type> typeDtoToEnum = new Converter<LinkDto, Type>() {
		public Type convert(MappingContext<LinkDto, Type> context) {
			return context.getSource() == null ? null : Type.valueOf(context.getSource().getName().toUpperCase());
		}
	};
	
	private static Converter<Collection<TypeDto>, Collection<Type>> typeDtoCollectionToEnumCollection = new Converter<Collection<TypeDto>, Collection<Type>>() {
		public Collection<Type> convert(MappingContext<Collection<TypeDto>, Collection<Type>> context) {
			if(context.getSource() == null) {
				return null;
			}
			
			Collection<Type> types = new ArrayList<Type>();
			context.getSource().stream().forEach(t -> {
				types.add(Type.valueOf(t.getType().getName().toUpperCase()));
			});
			
			return types;
		}
	};
	
	private static Converter<Collection<StatDto>, Integer> getHealthFromStats = new Converter<Collection<StatDto>, Integer>() {
		public Integer convert(MappingContext<Collection<StatDto>, Integer> context) {
			return context.getSource() == null ? null : context.getSource().stream().filter(i -> i.getStat().getName().equals("hp")).collect(Collectors.toList()).get(0).getBase_stat();
		}
	};
}
