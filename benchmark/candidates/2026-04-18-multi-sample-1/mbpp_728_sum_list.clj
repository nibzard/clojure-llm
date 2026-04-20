(defn sum_list
  "	Write a function takes as input two lists [a_1,...,a_n], [b_1,...,b_n] and returns [a_1+b_1,...,a_n+b_n]."
  [lst1 lst2]
  (mapv + (or lst1 []) (or lst2 [])))