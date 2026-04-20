(defn merge
  "	Write a cljthon function which takes a list of lists, where each sublist has two elements, and returns a list of two lists where the first list has the first element of each sublist and the second one has the second."
  [lst]
  (let [[xs ys] (reduce (fn [[a b] [x y]]
                          [(conj a x) (conj b y)])
                        [[] []]
                        lst)]
    (list xs ys)))