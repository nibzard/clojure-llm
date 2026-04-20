(defn is_happy
  "You are given a string s.
  Your task is to check if the string is hapclj or not.
  A string is hapclj if its length is at least 3 and every 3 consecutive letters are distinct
  For example:
  >>> (is_happy \"a\")
  false
  >>> (is_happy \"aa\")
  false
  >>> (is_happy \"abcd\")
  true
  >>> (is_happy \"aabb\")
  false
  >>> (is_happy \"adb\")
  true
  >>> (is_happy \"xyy\")
  false"
  [s]
  (let [chars (seq s)]
    (and (>= (count chars) 3)
         (every?
          (fn [[a b c]])
          (map (fn [[a b c]] (= 3 (count #{a b c})))
               (partition 3 1 chars))))))