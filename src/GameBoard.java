import java.awt.GridLayout;
import javax.swing.*;

public class GameBoard 
{
	String green,stone,purple;
	JFrame frame;
	JOptionPane popup;
	JFrame mssgFrame;
	JPanel panel;
	JLabel timerLabel,actualTimer;
	JButton[][] boardTile= new JButton[8][8];
	public GameBoard() 
	{

		
		frame = new JFrame();
		mssgFrame=new JFrame();
		panel= new JPanel();
		GridLayout layout =new GridLayout(9,9);
		
		frame.setBounds(200, 250, 430, 430);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Snorkels");
		panel.setLayout(layout);
		
		for(int col =0; col<=7;col++)
		{
			for(int row=0;row<=7;row++)
			{
				boardTile[col][row]= new JButton();
				boardTile[col][row].setEnabled(true);
				panel.add(boardTile[col][row]);
				
			}
		}
		timerLabel= new JLabel();
		timerLabel.setEnabled(true);
		actualTimer=new JLabel();
		actualTimer.setEnabled(true);
		panel.add(timerLabel);
		panel.add(actualTimer);
		for (int k=1;k<=7;k++)
		{
			boardTile[0][k].setText(Integer.toString(k));
			boardTile[k][0].setText(Integer.toString(k));
		}
		
		boardTile[0][0].setText("x,y");
		frame.getContentPane().add(panel);
		frame.setVisible(true);	
		
	}
	public void terminate()
	{
		frame.dispose();
	}
	public void setSnorkelTile(String colour,int x, int y)
	{
		if(colour=="Green")
		{
			boardTile[x][y].setIcon(new ImageIcon("green.png"));
		}
		if(colour=="Purple")
		{
			boardTile[x][y].setIcon(new ImageIcon("purple.png"));
		}
	}
	public void setTileBlank(int x, int y)
	{
		boardTile[x][y].setIcon(null);
	}
	public void setStoneTile(int x , int y)
	{
		
		boardTile[x][y].setIcon(new ImageIcon("stone.png"));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.repaint();
	}

	public void timer(String timeRemaining)
	{
		timerLabel.setText("Timer: ");
		actualTimer.setText(timeRemaining);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.repaint();
		
	}
}
