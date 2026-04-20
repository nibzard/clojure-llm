(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (let [v (vec (or nums []))
        n (count v)]
    (if (<= n 1)
      (seq v)
      (letfn [(swap-vec [vv i j]
                (assoc vv i (vv j) j (vv i)))]
        (loop [gap n
               swapped true
               vv v]
          (let [gap' (max 1 (int (Math/floor (/ gap 1.3))))
                [swapped' vv']
                (loop [i 0
                       s false
                       cur vv]
                  (if (< (+ i gap') n)
                    (if (> (cur i) (cur (+ i gap')))
                      (recur (inc i) true (swap-vec cur i (+ i gap')))
                      (recur (inc i) s cur))
                    [s cur]))]
            (if (and (= gap' 1) (not swapped'))
              (seq vv')
              (recur gap' swapped' vv')))))))