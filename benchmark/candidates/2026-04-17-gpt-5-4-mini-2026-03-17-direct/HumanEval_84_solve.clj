(defn solve
  [N]
  (Integer/toBinaryString (reduce + (map #(Character/digit ^char % 10) (str N)))))