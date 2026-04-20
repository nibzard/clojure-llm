(defn match_parens
  [lst]
  (let [[a b] lst
        good? (fn [s]
                (loop [chars (seq s), bal 0]
                  (cond
                    (neg? bal) false
                    (nil? chars) (zero? bal)
                    :else (recur (next chars)
                                 (+ bal (if (= (first chars) \() 1 -1))))))]
    (if (or (good? (str a b))
            (good? (str b a)))
      "Yes"
      "No")))