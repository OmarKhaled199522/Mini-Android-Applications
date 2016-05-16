package com.example.tabular_method;

//import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import android.R.integer;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;


public class tabular {

	
	Scanner input = new Scanner(System.in);
	
	
	int gui = 0;
	Object arr[] = new Object [100000];
	Object store_prime[] = new Object [1000];
	int num_of_groups = 0;
	int num_of_variables;
	int minterm [];
	int num_of_max_minterms;
	int actual_num_of_minterms;
	int not_care [];
	boolean flag_for_not_care;
	int num_of_not_care;
	int counter= 1;
	int l = 0;
	int s = 0;
	boolean vis_first[];
	int index_of_implicant;
	int num_of_canceled [] = new int [100000];
	int compare1[] = new int [100];
	int compare2 [] = new int [100];
	boolean prime_minterms [];
	boolean table [][];
	int num_of_every_prime [];
	int cont_x = 0;
	int x_num_col [];
	int print_essential[] = new int [1000];
	int size_of_eesential = 0;
	int change1 = 0 , change2 = 0;
	String pet_sol [] = new String [10000 + 10];
	int sol_num = 0;
	String mini_sol [] = new String [10000 + 10];
	int sol_mini = 0;
	String  fine = "F = ";
	String take = "";
	int read = 2;
	
	public void cancel (Single_Linkedlist q){
		
		for(int i = 0; i < l; i++){
			
			if(arr[i].equals(q)){
				num_of_canceled[i] = 1;
			}
			
		}
	}
	
	
	/*public void initialize (){
		
		for(int i = 0; i < 100000; i++)
			arr[i] = new Object();
	}*/
	
	
	
	public boolean check_of_vaild0(int i , int j){
		
		//if(not_care[i] == 1 && not_care[j] == 1) return false; // wrong condition
		if(j < i){
			int temp = i;
			i = j;
			j = temp;
		}
		String s1 = Integer.toBinaryString(i);
		String s2 = Integer.toBinaryString(j);
		int num1_of_one = 0, num2_of_one = 0;
		for(int h = 0; h < s1.length(); h++){
			if(s1.charAt(h) == '1') num1_of_one++;
		}
		for(int h = 0; h < s2.length(); h++){
			if(s2.charAt(h) == '1') num2_of_one++;
		}
		
		if(Math.abs(num1_of_one - num2_of_one ) != 1 || num1_of_one > num2_of_one) return false; //check for same groups

		/*int num_of_diff = Math.abs( s1.length()  - s2.length());
		
		for(int r = 0; r < num_of_diff; r++){
			if(s1.length() < s2.length()) s1 += '0';
			else s2 += '0';
		}
		num_of_diff = 0;
		
		for(int k = 0 ; k < s1.length() ; k++){
			if(s1.charAt(k) != s2.charAt(k)) num_of_diff++;
		}
		
		if(num_of_diff != 1) return false;
		return true;*/
		
		int dif = j - i;
		if(dif == 1) return true;
		if(dif % 2 == 1) return false;
		while(dif > 1){
			if(dif % 2 != 0) return false;
			dif /= 2;
		}
		return true;
	}
	
	public boolean check_for_big_pair(Single_Linkedlist l1 , Single_Linkedlist l2){
		
		int con = counter;
		while(--con > 0){
			if(l1.get(con).equals(l2.get(con)) == false) return false;
		}
		return true;
	}
	
	public void input () throws IOException{
		
		String ar [];
		System.out.println("1- READ FROM FILE\n2- USER INPUT");
		if (gui != 1) read  = input.nextInt();
		if(read == 1) gui = 1;
		
		if(gui == 1){
			if (read == 1){
				
				BufferedReader h = new BufferedReader(new FileReader("file.txt"));
				ar = h.readLine().split(",");
				
			} else  ar  = take.split(","); 
			
			
			num_of_variables = Integer.parseInt(ar[0]);
			actual_num_of_minterms = Integer.parseInt(ar[1]);
			num_of_max_minterms =  (int) Math.pow(2, num_of_variables);
			
			minterm = new int [num_of_max_minterms + 1];
			not_care = new int [num_of_max_minterms + 1];
			vis_first = new boolean [100000];
			prime_minterms = new boolean [num_of_max_minterms + 1];
			x_num_col = new int [num_of_max_minterms + 1];
			
			
			int i = 0;
			for(i = 2; i < actual_num_of_minterms + 2; i++){
				System.out.println(Integer.parseInt(ar[i]));
				minterm[ Integer.parseInt(ar[i]) ] = 1;
			}
			
			int temp = 	Integer.parseInt(ar[i++]);
			while (temp-- > 0){
				minterm[ Integer.parseInt(ar[i]) ] = 1;
				not_care [ Integer.parseInt(ar[i++])] = 1;
			}
			num_of_max_minterms++;
			
		} else {
		
		System.out.print("Enter the number of variables: ");
		num_of_variables = input.nextInt();
		num_of_max_minterms =  (int) Math.pow(2, num_of_variables);
		num_of_max_minterms ++; // to catch last element
		
		minterm = new int [num_of_max_minterms];
		not_care = new int [num_of_max_minterms];
		vis_first = new boolean [100000];
		prime_minterms = new boolean [num_of_max_minterms];
		x_num_col = new int [num_of_max_minterms];
		
		System.out.println("Enter the number of minterms: ");
		actual_num_of_minterms = input.nextInt();
		
		for(int i = 0; i <actual_num_of_minterms ; i++){
			int temp = input.nextInt();
			minterm[temp] = 1;
		}
		
		
		System.out.println("Enter the number of don't care minterms or 0 otherwise");
		int temp = num_of_not_care =  input.nextInt();
		if(temp > 0){
			flag_for_not_care = true;
			
			for(int i = 0; i < temp; i++){
				int dum = input.nextInt();
				minterm[dum] = 1;
				not_care [dum] = 1;
			}	
		}
		}
		
		System.out.println("all possible groups");
		while (true){
			
			System.out.println("Grouping " + counter);
			System.out.println("-----------------------------");
			Single_Linkedlist b = new Single_Linkedlist();
			boolean is_there_pair = false;
			if(counter == 1){
				
				for(int i = 0; i < num_of_max_minterms; i++){
					for(int j = i + 1; j < num_of_max_minterms; j++){
						
						if(minterm[i] == 1 && minterm [j] == 1){
							
							if (check_of_vaild0(i , j)){
								vis_first[index_of_implicant++] = vis_first[index_of_implicant++] = true;
								prime_minterms[i] = prime_minterms[j] = true;
								is_there_pair = true;	
								arr[l] = new Single_Linkedlist();
								((Single_Linkedlist) arr[l]).add(i);
								((Single_Linkedlist) arr[l++]).add(j - i);
								System.out.println(i + " , " +  (j - i) );
							}
						}
						
					}
				}
				
				/*for(int i = 0; i < num_of_max_minterms; i++){
					for(int j = i + 1; j < num_of_max_minterms; j++){
						
						if(not_care[i] == 1 && minterm [j] == 1 && prime_minterms[j] == false){
							
							if (check_of_vaild0(i , j)){
								vis_first[index_of_implicant++] = vis_first[index_of_implicant++] = true;
								prime_minterms[i] = prime_minterms[j] = true;
								is_there_pair = true;	
								arr[l] = new Single_Linkedlist();
								((Single_Linkedlist) arr[l]).add(i);
								((Single_Linkedlist) arr[l++]).add(j - i);
								System.out.println(i + " , " +  (j - i) );
							}
						}
						
					}
				}*/
				
			} else {
				
				for(int i = 0; i < l; i++){
					
					for(int j = i + 1; j < l; j++){
						
						//System.out.println(  ((Single_Linkedlist) arr[i]).size()         );
						if(  ((Single_Linkedlist) arr[i]).size() == counter && ((Single_Linkedlist) arr[j]).size() == counter){
							
							if(  check_for_big_pair( (Single_Linkedlist)arr[i]  ,  (Single_Linkedlist)   arr[j])){
								if( check_of_vaild0( (Integer) ((Single_Linkedlist) arr[j]).get(0), (Integer) ((Single_Linkedlist) arr[i]).get(0))  ){
									vis_first[index_of_implicant++] = vis_first[index_of_implicant++] = true;
									cancel(  (Single_Linkedlist) arr[i] );
									cancel( (Single_Linkedlist) arr[j] );
									is_there_pair = true;	
									int tempo =  (Integer) ((Single_Linkedlist) arr[j]).get(0) - (Integer) ((Single_Linkedlist) arr[i]).get(0);
									arr[l] = new Single_Linkedlist();
									//arr[l] = arr[i];
									
									Node q = ((Single_Linkedlist) arr[i]).getHead();
									
									while(q != null){
										((Single_Linkedlist) arr[l]).add (q.value);
										q = q.next;
									}
									
									((Single_Linkedlist) arr[l++]).add(tempo);
									((Single_Linkedlist) arr[l - 1]).print();
								}
							}
							
						}
					}
				}
			}
			if(is_there_pair == false) break;
		
		counter ++;	
		b.clear();	
		}
		
		System.out.println("------------------------------");
		prime_implicant();
	}
	
	
	public void memset(){
		
		for(int i = 0; i < 100; i++)
			compare1[i] = compare2[i] = 0;
	}
	
	public boolean equality (Single_Linkedlist l1 , Single_Linkedlist l2){
		
		memset();
		if( (Integer)l1.get(0) != (Integer)l2.get(0)) return false;
		for(int i = 1; i < l1.size(); i++){
			compare1[  (Integer) l1.get(i)] = 1;
			compare2[  (Integer) l2.get(i)] = 1;
		}
		
		for(int i = 0; i < 100; i++){
			if(compare1[i] != compare2[i]) return false;
		}
		return true;
	}
	
	
	public void prime_implicant(){
		
		for(int i = 0; i < l; i++){
			
			int enter = 0;
			Single_Linkedlist che_not_care = new Single_Linkedlist();
			che_not_care = (Single_Linkedlist) arr[i];
			Node co = che_not_care.getHead();
			int summ = 0;
			while(co != null){
				if (not_care[ summ + (Integer) co.value] != 1){
					enter = 1;
				}
				summ +=  (Integer) co.value;
				co = co.next;
			}
			if (enter == 0) num_of_canceled[i] = 1;
			
			for(int j = i + 1; j < l && num_of_canceled[i] != 1; j++){
				if(((Single_Linkedlist) arr[i]).size() ==  ((Single_Linkedlist) arr[j]).size() && num_of_canceled [j] != 1){
					//System.out.println( ((Single_Linkedlist) arr[i]).size() + "  "  + ((Single_Linkedlist) arr[j]).size()  );
					if( equality(  (Single_Linkedlist) arr[i]  , (Single_Linkedlist ) arr[j] ) )
						num_of_canceled[j] = 1;
				}
			}
		}
		System.out.println("Prime Implicant:\n-------------------------");
		
		for(int i = 0; i < num_of_max_minterms; i++){
			if(minterm[i] == 1  && not_care[i] != 1 && prime_minterms[i] == false){
				store_prime[s] = new Single_Linkedlist();
				((Single_Linkedlist) store_prime[s++]).add(i);
				System.out.println(i);
			}
		}
		
		for(int i = 0; i < l; i++){
			if(num_of_canceled[i] != 1){
				((Single_Linkedlist) arr[i]).print();
				store_prime[s] = new Single_Linkedlist();
				store_prime[s++] = arr[i];
			}
		}
		table = new boolean [s][num_of_max_minterms];
		num_of_every_prime = new int [s];
		set_table();
	}
	
	public void essential (){
		
		while(cont_x > 0){
			
		int happen = 0;
		for(int i = 0; i < num_of_max_minterms; i++){
			
			if(x_num_col[i] == 1){
				x_num_col [i] = 0;
				happen = 1;
				
				for(int j = 0; j < s; j++){
					if(table[j][i] == true){
						print_essential[size_of_eesential++] = j;
						table[j][i] = false;
						cont_x--;
						num_of_every_prime[j]--;
						
						for(int k = 0; k < num_of_max_minterms; k++){
							if(table[j][k] == true){
								table[j][k] = false;
								cont_x--;
								x_num_col[k]--; num_of_every_prime[j]--;
								
								for(int h = 0; h < s; h++){
									if(table[h][k] == true){
										table[h][k] = false;
										cont_x--;
										x_num_col[k]--; num_of_every_prime[h]--;
									}
								}
								
							}
						}
						
					}
				}
			}	
		}
		if(cont_x > 0){
			
			row_dominant();
			if(cont_x > 0) col_dominant();
			if(change1 != 1 && change2 != 1){
				System.out.println("Petrick");
				petrick(0, 0);
				choose_shortest();
				break;
			}
			change1 = 0; change2 = 0;
			
		} else if (cont_x == 0)  break;
		}
	}
	
	public void set_table(){
		
		for(int i = 0; i < s ; i++){
			
			int sum = 0;
			Single_Linkedlist g = new Single_Linkedlist();
			g = (Single_Linkedlist) store_prime[i];
			Node  f = g.getHead();
			Node org = g.getHead();
			int num = 0;
			while(f != null){
				
				if( (f == g.getHead() && not_care [ (Integer) f.value ] == 1)   || (f != g.getHead() && not_care[ (Integer) f.value + (Integer) org.value ] == 1)) {f = f.next;continue;}
				
				
				if(f != g.getHead())   table[i][(Integer) f.value + (Integer) org.value] = true;
				else  table [i][ (Integer) org.value] = true;
				sum += (Integer) f.value;
				num++;
				if (f != g.getHead())  x_num_col [(Integer) f.value + (Integer) org.value]++;
				else				   x_num_col [(Integer) org.value]++;
				f = f.next;
			}
			if(num > 2) num++;
			cont_x += num;
			num_of_every_prime[i] = num;
			table[i][sum] = true;
			if(num > 2) x_num_col [ sum ]++;
		}
		essential();
		final_print();
	}
	
	public void final_print(){
		System.out.println("Essentials\n-----------------------------------");
		for(int i = 0; i < size_of_eesential; i++)
			System.out.println(print_essential[i] + " ");
		
		char ch [] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'};
		
		System.out.println("Result\n---------------------------------------");
		for(int i = 0; i < size_of_eesential; i++){
			
			int siz= num_of_variables;
			boolean which [] = new boolean [siz];
			Single_Linkedlist l = new Single_Linkedlist();
			l = (Single_Linkedlist) store_prime[  print_essential[i]  ];
			
			String s1 = Integer.toBinaryString( (Integer) l.getHead().value );
			/*while(s1.length() < siz){
				s1 += '0';
			}*/
			Node q = l.getHead().next;
			
			while(q != null){
				int temp = (Integer) q.value;
				if(temp == 1){
					which [siz -1] = true;
				} else {
					int co = 0;
					while(temp != 1){
						temp /= 2;
						co++;
					}
					which[siz - 1 -  co] = true;
				}
				q = q.next;
			}
			String res = "";
			for(int h = 0, x = s1.length(); h < siz ; h++){
				if(which[h] == false){
					res += ch[h];
					if(siz  - 1 -  h < s1.length()){
						if(s1.charAt(s1.length() - 1 - (siz - 1 - h) ) == '0') res += '`';
					} else res += '`';
				}
			}
			System.out.print(res);
			fine += res;
			if(i != size_of_eesential - 1) {System.out.print(" + "); fine += " + ";}
		}
		if(actual_num_of_minterms == 0) {fine += "0"; System.out.println(fine);}
		else if ( (actual_num_of_minterms == Math.pow(num_of_variables , 2) || fine.length() == 4) && (num_of_variables != 1) ) {fine += "1"; System.out.println(fine);}
		
	}
	
	public void col_dominant(){
		
		for(int i = 0; i < num_of_max_minterms && cont_x > 0; i++){
			for(int j = 0; j < num_of_max_minterms && x_num_col[i] > 0; j++){
				
				int found = 0;
				if(i == j  || x_num_col[i] >= x_num_col[j])continue;
				else {
					
					for(int k = 0; k < s; k++){
						if(table[k][i] == true && table[k][j] == false){
							found = 1;
							break;
						}
					}
					if(found == 0){
						change2 = 1;
						for(int b = 0; b < s; b++){
							if(table[b][j] == true){
								table[b][j] = false;
								num_of_every_prime[b]--;
							}
						}
						cont_x -= x_num_col[j];
						x_num_col[j] = 0;
					}
					
				}
			}
			
			
		}
	}
	
	public boolean possible_or_not(String st){
		
		for(int i = 0; i < num_of_max_minterms; i++){
			
			if(x_num_col[i] > 0){ // error
				int found = 0;
				
				for(int j = 0; j < s; j++){
					if(table[j][i] == true){
						
							if(j >= st.length()) return false;
							if(st.charAt(st.length() - 1 - j) == '1' ){
								
								found = 1;
								break;
							}
					}
				}
				if(found == 0) return false;
			}
		}
		return true;
	}
	
	public void petrick(int i, int st){
		
		if(i  == s){
			String b = Integer.toBinaryString(st);
			if (possible_or_not(b)){
				mini_sol[ sol_mini++] = b;
			}
		} else {
		
			petrick(i + 1, st | 1 << i);
			petrick(i + 1, st);
		}
	}
	
	public void choose_shortest(){
		
		int sh = 100000000;
		String best = mini_sol[sol_mini];
		
		for(int i = 0; i < sol_mini; i++){
			int num = 0;
			for(int j = 0; j < mini_sol[i].length(); j++){
				if(mini_sol[i].charAt(j) == '1') num++;
			}
			if(num < sh){
				sh = num;
				best = mini_sol[i];
			}
		}
		
		for(int i = 0, j = best.length() - 1; i < best.length(); i++, j--){
			if(best.charAt(i) == '1')print_essential[size_of_eesential ++] = j;
		}
		
	}
	
	public void row_dominant(){
			
			for(int i = 0; i < s && cont_x > 0; i++){
				
				int found = 0;
				for(int j = 0; j < s && num_of_every_prime[i] > 0 && found == 0; j++){
					
					if(num_of_every_prime[i] >= num_of_every_prime[j] || i == j) continue;
					else {
						
						for(int k = 0; k < num_of_max_minterms; k++){
							if( table[i][k] == true && table [j][k] == false ){
								found = 1;
								break;
							}
						}
						if(found == 0){
							change1 = 1;
							for(int k = 0; k < num_of_max_minterms; k++)
								if(table [i][k] == true){
									
									table[i][k] = false;
									x_num_col[k]--;
								}
							cont_x -= num_of_every_prime[i];
							num_of_every_prime [i] = 0;   
						}
					}
				}
			}
	}
}

