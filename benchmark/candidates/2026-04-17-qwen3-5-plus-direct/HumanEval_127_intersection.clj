(defn intersection
  [interval1 interval2]
  (let [[start1 end1] interval1
        [start2 end2] interval2
        intersect-start (max start1 start2)
        intersect-end (min end1 end2)
        length (- intersect-end intersect-start)]
    (if (or (<= length 1)
            (some #(zero? (mod length %)) (range 2 (inc (Math/sqrt length)))))
      "NO"
      "YES")))