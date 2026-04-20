(defn solve [N]
  (let [digit-sum (reduce + (map #(- (int %) 48) (str N)))]
    (Integer/toBinaryString digit-sum)))