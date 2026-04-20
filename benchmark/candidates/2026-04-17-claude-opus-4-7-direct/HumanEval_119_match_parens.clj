(defn match-parens
  [lst]
  (let [[s1 s2] lst
        check (fn [s]
                (loop [chars s
                       depth 0
                       min-depth 0]
                  (if (empty? chars)
                    [depth min-depth]
                    (let [d (if (= \( (first chars)) 1 -1)
                          new-depth (+ depth d)]
                      (recur (rest chars) new-depth (min min-depth new-depth))))))
        [d1 m1] (check s1)
        [d2 m2] (check s2)
        ;; Try order 1: s1 then s2
        ;; Valid if s1 never goes below 0 (m1 >= 0) and s2 never goes below -d1
        try1 (and (>= m1 0) (>= (+ m2 (- d1)) 0))
        ;; Try order 2: s2 then s1
        ;; Valid if s2 never goes below 0 (m2 >= 0) and s1 never goes below -d2
        try2 (and (>= m2 0) (>= (+ m1 (- d2)) 0))
        ;; Final balance must be 0
        balanced? (zero? (+ d1 d2))]
    (if (and balanced? (or try1 try2))
      "Yes"
      "No")))