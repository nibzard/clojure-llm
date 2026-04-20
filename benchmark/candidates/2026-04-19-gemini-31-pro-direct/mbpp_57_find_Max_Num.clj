(defn find_Max_Num [arr]
  (when (seq arr)
    (->> arr
         (map str)
         (sort (fn [a b] (compare (str b a) (str a b))))
         (apply str)
         (Long/parseLong))))