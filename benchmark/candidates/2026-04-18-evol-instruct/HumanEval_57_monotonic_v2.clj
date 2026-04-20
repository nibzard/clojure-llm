(defn monotonic-by
  "Return true when the elements of coll are monotonic according to key-fn.

  The values produced by key-fn must be either nondecreasing or nonincreasing.

  Works with any sequential collection and ignores the original element values
  except through key-fn.

  Examples:
  >>> (monotonic-by count [\"a\" \"bb\" \"ccc\"])
  true
  >>> (monotonic-by count [\"aaa\" \"bb\" \"c\"])
  true
  >>> (monotonic-by identity [3 1 2])
  false
  >>> (monotonic-by #(Math/abs %) [-3 -2 -1 0 1])
  true"
  [key-fn coll]
  (let [xs (map key-fn coll)]
    (or (apply <= xs)
        (apply >= xs))))