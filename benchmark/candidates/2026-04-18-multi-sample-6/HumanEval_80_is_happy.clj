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
  (and (string? s)
       (<= 3 (count s))
       (every? (fn [[a b c]]
                 (and (not= a b)
                      (not= a c)
                      (not= b c)))
               (partition 3 1 s))))