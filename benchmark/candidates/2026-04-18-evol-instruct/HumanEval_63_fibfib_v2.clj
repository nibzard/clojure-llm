(defn run-length-encode
  "Return a run-length encoded vector of [item count] pairs for the input collection.

  The function must work with any sequential collection, including lazy and infinite
  sequences when only a finite prefix is taken before calling this function.

  Examples:
  >>> (run-length-encode [1 1 1 2 2 3])
  [[1 3] [2 2] [3 1]]

  >>> (run-length-encode \"aaabbc\")
  [[\\a 3] [\\b 2] [\\c 1]]

  >>> (run-length-encode [])
  []

  >>> (run-length-encode nil)
  []"
  [coll]
  (if-let [s (seq coll)]
    (let [step (fn [{:keys [acc cur cnt]} x]
                 (if (= x cur)
                   {:acc acc :cur cur :cnt (inc cnt)}
                   {:acc (conj acc [cur cnt]) :cur x :cnt 1}))
          {:keys [acc cur cnt]} (reduce step {:acc [] :cur (first s) :cnt 0} s)]
      (conj acc [cur cnt]))
    []))