(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (let [v (vec nums)
        n (count v)
        shrink 1.3]
    (if (<= n 1)
      nums
      (letfn [(next-gap [gap]
                (let [g (int (/ gap shrink))]
                  (if (< g 1) 1 g)))
              (pass [arr gap]
                (loop [i 0
                       a arr
                       swapped? false]
                  (if (< (+ i gap) n)
                    (let [j (+ i gap)]
                      (if (pos? (compare (nth a i) (nth a j)))
                        (recur (inc i) (assoc a i (nth a j) j (nth a i)) true)
                        (recur (inc i) a swapped?)))
                    [a swapped?]))]
        (let [[sorted-v _]
              (loop [gap n
                     arr v
                     swapped? true]
                (if (or (> gap 1) swapped?)
                  (let [g (next-gap gap)
                        [a s?] (pass arr g)]
                    (recur g a s?))
                  [arr swapped?]))]
          (if (vector? nums)
            sorted-v
            (seq sorted-v))))))