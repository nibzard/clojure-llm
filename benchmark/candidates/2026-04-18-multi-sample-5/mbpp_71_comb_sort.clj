(defn comb_sort
  "	Write a function to sort a list of elements."
  [nums]
  (let [v (vec (or nums []))
        n (count v)
        shrink-factor 1.3
        next-gap (fn [gap]
                   (let [g (int (Math/floor (/ gap shrink-factor)))]
                     (if (< g 1) 1 g)))]
    (if (<= n 1)
      (seq v)
      (letfn [(pass [arr gap]
                (loop [i 0
                       a arr
                       swapped? false]
                  (if (< (+ i gap) n)
                    (let [x (nth a i)
                          y (nth a (+ i gap))]
                      (if (> x y)
                        (recur (inc i) (assoc a i y (+ i gap) x) true)
                        (recur (inc i) a swapped?)))
                    [a swapped?])))
              (sort-loop [arr gap swapped?]
                (if (or (> gap 1) swapped?)
                  (let [g (next-gap gap)
                        [a s?] (pass arr g)]
                    (recur a g s?))
                  arr))]
        (seq (sort-loop v n true))))))