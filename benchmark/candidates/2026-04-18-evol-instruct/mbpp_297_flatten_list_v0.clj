(defn flatten-limited
  "Return a flattened version of a nested list, but only to a given depth.

  Examples:
  (flatten-limited '((1 (2 3)) (4 5)) 1) => '(1 (2 3) 4 5)
  (flatten-limited '((1 (2 3)) (4 5)) 2) => '(1 2 3 4 5)
  (flatten-limited nil 3) => nil"
  [coll depth]
  (letfn [(step [xs d]
            (cond
              (nil? xs) nil
              (zero? d) xs
              (sequential? xs)
              (mapcat #(if (sequential? %) (step % (dec d)) (list %)) xs)
              :else (list xs)))]
    (when coll
      (apply list (step coll depth)))))