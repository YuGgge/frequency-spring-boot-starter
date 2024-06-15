
--TODO  更改部分逻辑

-- 漏桶限流
local key = KEYS[1]                         -- 用于标识请求类型或用户的key
local maxCount = tonumber(ARGV[1])          -- 最大请求次数
local timeRange = tonumber(ARGV[2])         -- 滑动窗口的大小（秒）
local currentTime = tonumber(ARGV[3])       -- 当前请求的时间戳（毫秒）

-- 获取漏桶当前容量
local currentCount = tonumber(redis.call('get', KEYS[1])) or 0

-- 如果漏桶容量超过最大容量，则拒绝请求
if currentCount+1 > maxCount then
    return 0
end

-- 上一次请求的时间戳（毫秒）
local lastRequestTime = tonumber(redis.call('get', KEYS[2])) or 0

-- 计算漏水量（即，上一次请求至当前时间的时间差）
local leak = (currentTime - lastRequestTime) * maxCount / timeRange *1000

-- 更新漏桶容量，取当前容量和漏水量之间的最小值作为新的容量
local newCount = math.max(0, currentCount - leak) + 1

-- 更新漏桶容量
redis.call('set', KEYS[1], newCount)
-- 更新上一次请求时间
redis.call('set', KEYS[2], currentTime)
return 1

