(defn add_elements [arr k]
  (->> arr
       (take k)
       (filter #(< -100 % 100))
       (reduce +)))