(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (let [v (vec nums)
        n (count v)
        shrink-factor 1.3
        step (fn [arr gap]
               (loop [i 0
                      a arr
                      swapped? false]
                 (if (< (+ i gap) n)
                   (let [x (nth a i)
                         y (nth a (+ i gap))]
                     (if (> x y)
                       (recur (inc i) (-> a
                                          (assoc i y)
                                          (assoc (+ i gap) x))
                              true)
                       (recur (inc i) a swapped?)))
                   [a swapped?])))]
    (loop [arr v
           gap n
           swapped? true]
      (if (or (> gap 1) swapped?)
        (let [next-gap (max 1 (int (/ gap shrink-factor)))
              [next-arr did-swap?] (step arr next-gap)]
          (recur next-arr next-gap did-swap?))
        (seq arr)))))