(defn move_one_ball
  [arr]
  (let [n (count arr)]
    (cond
      (zero? n) true
      (= 1 n) true
      :else
      (let [breaks (count (filter true?
                                  (map > arr (concat (rest arr) [(first arr)]))))]
        (<= breaks 1)))))