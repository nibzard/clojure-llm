(defn occurrence-substring-map
  "Return a map of each keyword to the number of times it appears in a collection.

  Ignore nil elements and treat the collection as a sequence of values, not a string.

  Examples:
  (occurrence-substring-map [:a :b :a nil :c :b :a])
  => {:a 3, :b 2, :c 1}

  (occurrence-substring-map [])
  => {}"
  [coll]
  (reduce (fn [m x]
            (if (nil? x)
              m
              (update m x (fnil inc 0))))
          {}
          coll))