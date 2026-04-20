(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (let [v (vec (or nums []))
        n (count v)]
    (if (<= n 1)
      (seq v)
      (letfn [(next-gap [gap]
                (let [g (quot (* gap 10) 13)]
                  (if (< g 1) 1 g)))
              (pass [arr gap]
                (loop [i 0
                       a arr
                       swapped? false]
                  (if (>= (+ i gap) n)
                    [a swapped?]
                    (let [j (+ i gap)]
                      (if (> (nth a i) (nth a j))
                        (recur (inc i) (assoc a i (nth a j) j (nth a i)) true)
                        (recur (inc i) a swapped?))))))]
        (loop [arr v
               gap n
               swapped? true]
          (if (or (> gap 1) swapped?)
            (let [g (next-gap gap)
                  [a s] (pass arr g)]
              (recur a g s))
            (seq arr))))))