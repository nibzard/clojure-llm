(defn add-map-values
  "Return a new map where each value is increased by the corresponding value in `delta-map`.

  Missing keys in `delta-map` are treated as 0.

  Examples:
  (add-map-values {:a 1 :b 2} {:a 10 :b -1 :c 5})
  => {:a 11 :b 1 :c 5}

  (add-map-values nil {:a 3})
  => {:a 3}

  (add-map-values {:x 4} nil)
  => {:x 4}"
  [base-map delta-map]
  (merge-with + (or base-map {}) (or delta-map {})))