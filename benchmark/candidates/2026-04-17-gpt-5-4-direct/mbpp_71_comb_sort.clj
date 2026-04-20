(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (let [v (vec (or nums []))
        n (count v)
        shrink 1.3]
    (if (<= n 1)
      (seq v)
      (letfn [(step [arr gap swapped?]
                (let [next-gap (max 1 (int (/ gap shrink)))]
                  (loop [a arr
                         i 0
                         did-swap false]
                    (if (< (+ i next-gap) n)
                      (let [x (nth a i)
                            y (nth a (+ i next-gap))]
                        (if (> x y)
                          (recur (assoc a i y (+ i next-gap) x) (inc i) true)
                          (recur a (inc i) did-swap)))
                      [a next-gap did-swap]))))]
        (loop [arr v
               gap n
               swapped? true]
          (if (or (> gap 1) swapped?)
            (let [[next-arr next-gap did-swap] (step arr gap swapped?)]
              (recur next-arr next-gap did-swap))
            (seq arr))))))