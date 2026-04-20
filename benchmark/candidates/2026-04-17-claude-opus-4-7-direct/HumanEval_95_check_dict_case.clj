(defn check_dict_case
  "Given a map, return true if all keys are strings in lower 
  case or all keys are strings in upper case, else return false.
  The function should return false is the given map is empty."
  [dict]
  (if (empty? dict)
    false
    (let [keys (keys dict)
          all-strings? (every? string? keys)]
      (and all-strings?
           (or (every? #(= % (clojure.string/lower-case %)) keys)
               (every? #(= % (clojure.string/upper-case %)) keys))))))