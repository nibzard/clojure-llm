(defn count_reverse_pairs
  "Write a function to count the pairs of reverse strings in the given string list."
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (loop [remaining freqs
           count-pairs 0]
      (if (empty? remaining)
        count-pairs
        (let [[[s cnt] & _] remaining
              rs (apply str (reverse s))]
          (cond
            (= s rs)
            (recur (dissoc remaining s)
                   (+ count-pairs (quot cnt 2)))

            (contains? remaining rs)
            (recur (-> remaining
                       (dissoc s)
                       (dissoc rs))
                   (+ count-pairs (min cnt (get remaining rs))))

            :else
            (recur (dissoc remaining s) count-pairs)))))))