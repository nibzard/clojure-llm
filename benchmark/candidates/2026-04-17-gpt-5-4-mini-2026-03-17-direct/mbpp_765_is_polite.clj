(defn is_polite
  "Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (let [n (long n)]
    (if (<= n 0)
      nil
      (->> (range 1 (inc n))
           (map #(- n %))
           (filter odd?)
           count))))