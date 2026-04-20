(defn sort_array
  [array]
  (if (empty? array)
    []
    (let [first-val (first array)
          last-val (last array)
          sum (+ first-val last-val)]
      (if (odd? sum)
        (vec (sort array))
        (vec (sort > array))))))