class Game
{
    Mover mover;
    Tile[] tiles;
    int width, height;
    
    Game()
    {
        mover = new Mover(5, 5);
        
        this.width = 12;
        this.height = 10;
        
        generateTiles();
    }
    
    void generateTiles()
    {
        tiles = new Tile[7];
        
        for (int i = 0; i < tiles.length; i++)
        {
            int randX = (int) (Math.random() * this.width);
            int randY = (int) (Math.random() * this.height);
            
            tiles[i] = new Tile(randX, randY);
        }
    }
    
    void update()
    {
        // Add the code that the game needs to run every update
        
        moveTilesDown();
    }
    
    void moveTilesDown()
    {
        for (Tile t : tiles)
        {
            // .....
        }
    }
}