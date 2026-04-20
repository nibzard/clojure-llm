(defn comb_sort
  "Write a function to sort a list of elements."
  [nums]
  (let [gap (count nums)
        shrink 1.3]
    (loop [arr (vec nums)
           gap gap
           swapped? false]
      (let [new-gap (int (max 1 (/ gap shrink)))
            len (count arr)]
        (if (and (= new-gap 1) (not swapped?))
          arr
          (let [;; Perform a single pass with the current gap
                [result-arr final-swapped?] 
                (loop [i 0
                       a arr
                       s? false]
                  (if (>= (+ i new-gap) len)
                    [a s?]
                    (let [v1 (nth a i)
                          v2 (nth a (+ i new-gap))]
                      (if (> v1 v2)
                        (recur (inc i)
                               (assoc a i v2 (+ i new-gap) v1)
                               true)
                        (recur (inc i) a s?)))))]
            (recur result-arr new-gap final-swapped?)))))))