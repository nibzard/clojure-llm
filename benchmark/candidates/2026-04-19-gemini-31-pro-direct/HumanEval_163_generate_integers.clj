(defn generate_integers [a b]
  (let [lower (min a b)
        upper (max a b)]
    (vec (filter #(and (even? %) (<= 0 % 9))
                 (range lower (inc upper))))))