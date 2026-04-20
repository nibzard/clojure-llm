(defn flatten_list
  "	Write a function to flatten a given nested list structure."
  [list1]
  (letfn [(flatten* [x]
            (cond
              (nil? x) ()
              (sequential? x) (mapcat flatten* x)
              :else (list x)))]
    (flatten* list1)))