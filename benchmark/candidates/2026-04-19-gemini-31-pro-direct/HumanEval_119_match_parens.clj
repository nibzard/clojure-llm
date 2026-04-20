(defn match_parens [[s1 s2]]
  (let [balanced? (fn [s]
                    (loop [chars (seq s)
                           balance 0]
                      (cond
                        (neg? balance) false
                        (empty? chars) (zero? balance)
                        :else (recur (rest chars)
                                     (if (= (first chars) \()
                                       (inc balance)
                                       (dec balance))))))]
    (if (or (balanced? (str s1 s2))
            (balanced? (str s2 s1)))
      "Yes"
      "No")))