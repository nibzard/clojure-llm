(defn get_total_number_of_sequences [m n]
  (loop [i 1
         prev-dp (vec (repeat (inc m) 1))]
    (if (> i n)
      (peek prev-dp)
      (let [next-dp (reduce (fn [acc j]
                              (conj acc (+ (peek acc)
                                           (prev-dp (quot j 2)))))
                            [0]
                            (range 1 (inc m)))]
        (if (zero? (peek next-dp))
          0
          (recur (inc i) next-dp))))))