(defn match_parens
  [lst]
  (let [s1 (first lst)
        s2 (second lst)
        
        balanced? (fn [s]
                    (loop [chars (seq s)
                           count 0]
                      (cond
                        (< count 0) false
                        (nil? chars) (= count 0)
                        :else (recur (next chars)
                                    (if (= (first chars) \()
                                      (inc count)
                                      (dec count))))))]
    
    (if (or (balanced? (str s1 s2))
            (balanced? (str s2 s1)))
      "Yes"
      "No")))