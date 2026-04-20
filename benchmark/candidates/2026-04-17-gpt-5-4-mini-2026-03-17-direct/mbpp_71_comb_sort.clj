(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(next-gap [gap]
            (max 1 (long (Math/floor (/ gap 1.3)))))]
    (loop [xs (vec nums)
           gap (count nums)
           swapped? true]
      (if (and (= gap 1) (not swapped?))
        xs
        (let [gap (next-gap gap)
              n (count xs)
              [ys swapped?]
              (loop [i 0
                     v xs
                     swapped? false]
                (if (>= (+ i gap) n)
                  [v swapped?]
                  (let [j (+ i gap)]
                    (if (> (compare (v i) (v j)) 0)
                      (recur (inc i)
                             (assoc v i (v j) j (v i))
                             true)
                      (recur (inc i) v swapped?)))))]
          (recur ys gap swapped?))))))