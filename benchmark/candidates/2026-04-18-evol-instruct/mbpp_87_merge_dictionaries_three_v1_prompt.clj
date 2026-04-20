(defn merge-dictionaries-variadic
  "Merge any number of maps into one map.

  Later maps override earlier ones for duplicate keys.

  Examples:
  (merge-dictionaries-variadic {:a 1} {:b 2} {:a 3})
  => {:a 3, :b 2}

  (merge-dictionaries-variadic)
  => {}"
  [& maps])