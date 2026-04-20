(defn fizz_buzz [n]
  (->> (range n)
       (filter #(or (zero? (mod % 11)) (zero? (mod % 13))))
       (mapcat str)
       (filter #{\7})
       count))