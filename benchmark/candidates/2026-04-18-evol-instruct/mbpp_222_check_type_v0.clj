(defn same-shape?
  "Return true if every nested collection in `xs` has the same shape as the first one.
  
  The function should compare collections recursively, ignoring the actual values.
  
  Examples:
  (same-shape? [[1 2] [3 4]])        ;=> true
  (same-shape? [[1 2] [3 4 5]])      ;=> false
  (same-shape? [[:a {:x 1}] [[:b] {:y 2}]]) ;=> true
  (same-shape? nil)                 ;=> true"
  [xs]
  (letfn [(shape [x]
            (cond
              (nil? x) :nil
              (map? x)  [:map (shape (seq x))]
              (sequential? x) [:seq (mapv shape x)]
              :else :atom))]
    (or (nil? xs)
        (apply = (map shape xs)))))