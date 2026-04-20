(defn even_odd_count [num]
  (->> num
       str
       (filter #(Character/isDigit %))
       (map #(Character/digit % 10))
       (reduce (fn [[evens odds] d]
                 (if (even? d)
                   [(inc evens) odds]
                   [evens (inc odds)]))
               [0 0])))