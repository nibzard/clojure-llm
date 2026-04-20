(defn flatten_list
  "	Write a function to flatten a given nested list structure."
  [list1]
  (letfn [(flat [x]
            (cond
              (nil? x) ()
              (sequential? x) (mapcat flat x)
              :else (list x)))]
    (flat list1)))