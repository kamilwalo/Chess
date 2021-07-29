package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import javax.management.ImmutableDescriptor;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
Wnioski: Tworzona jest klasa abstrakcyjna, w której tworzymy konstruktor, który natomiast przechowuje koordynaty
posiada on abstrakycjne metody, które sprawdzać mają czy pole jest okupowane, pobierają pionek

Wewnątrz tej klasy znajdują się klasy NIE abstrakcyjen, które implementują tą właśnie klase abstrakcyjną
*/
public abstract class Tile {
    protected final int tileCordinates;

    private static final Map<Integer,EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
        for (int i=0;i<64;i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }

        return Collections.unmodifiableMap(emptyTileMap);
    }

    private Tile(int tileCordinates) {

        this.tileCordinates = tileCordinates;
    }

    public static Tile createTile(final int titleCoordinate,final Piece piece){
        return piece != null ? new OccupiedTile(titleCoordinate, piece) : EMPTY_TILES.get(titleCoordinate); //jeśli true to wyrażenie 1
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{

        private EmptyTile(final int coordinate){
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;

        private OccupiedTile(final int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
