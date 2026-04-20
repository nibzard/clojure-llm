(defn rotate_right
  "Write a function to rotate a given list by specified number of items to the right direction."
  [list m]
  (let [cnt (count list)
        m (mod m cnt)]
    (if (or (zero? cnt) (zero? m))
      list
      (into (take m list) (drop m list)))))